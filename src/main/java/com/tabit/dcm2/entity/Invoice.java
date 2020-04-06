package com.tabit.dcm2.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Invoice implements IEntity {

    @Id
    @Column(name = "id")
    @TableGenerator(allocationSize = 1,
            name = "IDGenerator",
            table = "id_gen",
            pkColumnName = "id_name",
            valueColumnName = "id_value",
            pkColumnValue = "invoice_id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "IDGenerator")
    private Long id;

    // sequence should start from 1 every month id have mask YYYYMM_NNNN where NNNN number of invoice
    @Column(name = "invoice_id")
    private String invoiceId;

    @OneToOne
    @JoinColumn(name = "stay_id")
    private Stay stay;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    List<Payment> payments = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Stay getStay() {
        return stay;
    }

    public void setStay(Stay stay) {
        this.stay = stay;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public void addPayments(List<Payment> payments) {
        setPayments(payments);
        payments.forEach(i -> i.setInvoice(this));
    }
}
