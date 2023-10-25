package com.electric.billing.SlabBasedBilling.model.responses;

import com.electric.billing.SlabBasedBilling.model.responses.to.SlabReadingTo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class SlabReadingResponse extends GenericResponse{
    @JsonProperty("slabReadingToList")
    private List<SlabReadingTo> slabReadingTos;
}
