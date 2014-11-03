class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')

        "/hackapi/notifypayment" (controller: "hack", parseRequest: true) { action = [GET: "processPayment"] }
        "/hackapi/checkout" (controller: "hack", parseRequest: true) { action = [POST: "checkout"] }
        "/hackapi/list/$id"(controller: "hack", parseRequest: true) { action = [POST: "list", GET: "LIST"] }

        // Landing Page for form
        "/landing" (controller: "submitForm", action: "landing")
    }
}
