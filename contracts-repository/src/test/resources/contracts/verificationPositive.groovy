org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'POST'
        url '/v1/verification'
        body([
                "messageToRecruiter": "GOOD_APPLICATION"
        ])
    }
    response {
        status 200
        body([
                "status": "ACCEPTED"
        ])
        headers {
            contentType('application/json')
        }
    }
}