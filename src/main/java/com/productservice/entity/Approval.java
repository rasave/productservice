package com.productservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("requestedDate")
    @Column(name = "requestedDate")
    @Temporal(value=TemporalType.DATE)
    private Date requestedDate;

    @JsonProperty("productId")
    private Integer productId;

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
