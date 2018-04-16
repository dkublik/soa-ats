package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/v1/prefill/for-candidate/just_britney'
    }
    response {
        status 200
        body([
                "firstName": "Britney",
                "lastName": "Spears",
                "email": "just_britney@spears.pl",
                "yearOfExperience": 1
        ])
        headers {
            contentType('application/json')
        }
    }
}