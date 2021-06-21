package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("contract to obtain all available books")
    request {
        method("GET")
        urlPath("/books")
    }
    response {
        status OK()
        headers {
            header 'Content-Type': 'application/json'
        }
        body(file("response.json"))
    }
}