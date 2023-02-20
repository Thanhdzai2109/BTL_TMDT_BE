package org.sid.backendleDeal.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Table(name="shop")
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long idClient;
    private Integer amountSP;
    private String photoName;
    @OneToMany(mappedBy = "shop")
    private List<Offres> offrees;

}
