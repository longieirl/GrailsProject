package com.dublininterns.hack.filters

import com.dublininterns.hack.utils.LoggingFields
import com.dublininterns.hack.utils.PopulatableField
import grails.util.Environment
import org.apache.log4j.MDC

import java.util.concurrent.atomic.AtomicInteger

class UsageTrackingFilters {

    // counter used to assign a requestId to requests that have not been assigned a request Id yet.
    // for AJAX request we won't have to use this counter as the request ID will already have been assigned in
    static AtomicInteger requestCounter = new AtomicInteger();

    static dependsOn = [XssSanitizerFilters]

    def filters = {

        all(controller: '*', action: '*') {

            before = {

                // Dev-note: with the grails 2.3.9 upgrade the params are empty on the controller methods due to the httplogger closing the request stream
                // http://stackoverflow.com/questions/18870003/missing-parameters-with-restful-request-when-upgrading-to-grails-2-3-0
                if (request.JSON) {
                    log.debug("Populating parsed json to params")
                    params << request.JSON
                }

                MCAuditUtil.setDateField(PopulatableField.REQUEST_DATE_TIME, new Date());
                MCAuditUtil.setSourceMachine(getRemoteAddr(request) ?: "-", request.getRemotePort().toString())
                MCAuditUtil.setDestMachine(request.getServerName() ?: "-", request.getServerPort().toString())

                request.start = System.currentTimeMillis()
                def requestId = MDC.get(LoggingFields.REQUEST_ID) ?: InetAddress.getLocalHost().getHostName() + ":" + requestCounter.decrementAndGet()

                MDC.put(LoggingFields.REQUEST_USER, request.userPrincipal?.name ?: '')
                MDC.put(LoggingFields.REQUEST_URL_PATH, request.servletPath)
                MDC.put(LoggingFields.REQUEST_ID, requestId)
                MDC.put(LoggingFields.PORTAL_USER_ID, session.userid ?: '-')
            }
            after = {

                MDC.clear()

            }
            afterView = {

                MDC.remove 'startTime'
                MDC.remove LoggingFields.REQUEST_ID

                log.info("<< UsageTrackingFilters.afterView(), responseTime=${System.currentTimeMillis() - request.start}ms | Params= ${request.queryString ?: "-"}")

            }

        }

    }

    String getRemoteAddr(def request) {

        // From QkR
        String ip = request.getHeader("X-Teros-Client-IP")
        switch (Environment.current.name) {
            case "production":
            case "stage":
                // in these environments, assume X-Teros-Client-IP will always be present
                break
            default:
                // Defer to HttpServletRequest
                if (!ip) {
                    ip = request.getRemoteAddr()
                }
                break
        }
        ip

    }

}
