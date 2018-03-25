package pl.dk.soa.apply.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Application {

    private String candidateId;
    private String messageToRecruiter;
    private String listingId;

}
