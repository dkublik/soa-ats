package pl.dk.soa.prefill;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/v1/prefill/for-candidate/", produces = APPLICATION_JSON_VALUE)
@Api(description = "pre filling data")
class PrefillController {

    private Map<String, Prefill> prefillData = new HashMap<>();

    @GetMapping("{candidateId}")
    @ApiOperation(value = "get prefill data")
    ResponseEntity<Prefill> getPrefillData(@PathVariable String candidateId) {
        return Optional.ofNullable(prefillData.get(candidateId.toLowerCase()))
                .map(data -> new ResponseEntity(data, OK))
                .orElse(new ResponseEntity(NOT_FOUND));
    }

    @PostConstruct
    void fillSomeData() {
        prefillData.put("mhamill", new Prefill("Mark", "Hammil", "mark.hamill@gmail.com"));
        prefillData.put("just_britney", new Prefill("Britney", "Spears", "just_britney@spears.pl"));
        prefillData.put("mrpresident", new Prefill("Barack", "Obama", "mrPresident@wp.pl"));
    }

}
