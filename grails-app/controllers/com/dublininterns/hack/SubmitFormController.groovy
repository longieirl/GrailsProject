package com.dublininterns.hack


class SubmitFormController {

    static allowedMethods = [landing:'GET', save:['POST'], success: 'GET']

    def landing() {
        log.info(">>landing()")

        render view: "/landing/landing",
                model: [msg: 'Welcome', rememberMeParameter: false]
    }

    def save() {
        log.info(">>save()")

        log.info(">>save(), name=$params.firstName")

        redirect(action: 'success')
    }

    // dev-note: http://greggigon.com/2012/04/25/post-redirect-get-pattern-with-grails/
    def success() {
        log.info(">>success()")

        render view: "/landing/landing",
                model: [msg: 'User was updated', rememberMeParameter: false]
    }
}
