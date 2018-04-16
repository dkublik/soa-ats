org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'POST'
        url '/v1/verification'
        body(
                "messageToRecruiter": "BAD_APPLICATION"
        )
    }
    response {
        status 200
        body([
                "status": "REJECTED"
        ])
        headers {
            contentType('application/json')
        }
    }
}