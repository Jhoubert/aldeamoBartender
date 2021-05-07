package com.aldeamo.BartenderTest.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "arrays", schema="public")
public class BartenderEntity {

    @GenericGenerator(
            name = "bartenderGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "bartender_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
            }
    )

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "bartenderGenerator")
    private long id;

    @Column(name = "input_array")
    private String input_array;

    public BartenderEntity() {}
    public BartenderEntity(long id, String input_array) {
        this.id = id;
        this.input_array = input_array;
    }

    public long getId() {
        return id;
    }

    public String getInput_array() {
        return this.input_array;
    }

}
