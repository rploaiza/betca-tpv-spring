package es.upm.miw.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.upm.miw.documents.core.CashierClosure;

public class CashierClosureLastOutputDto {

    private boolean closed;

    // @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MMM-yyyy HH:mm")
    @JsonInclude(Include.NON_NULL)
    private Date closureDate;

    public CashierClosureLastOutputDto() {
        // Empty for framework
    }

    public CashierClosureLastOutputDto(boolean closed, Date closureDate) {
        super();
        this.closed = closed;
        this.closureDate = closureDate;
    }

    public CashierClosureLastOutputDto(CashierClosure cashierClosure) {
        this(cashierClosure.isClosed(), cashierClosure.getClosureDate());
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Date getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(Date closureDate) {
        this.closureDate = closureDate;
    }

    @Override
    public String toString() {
        return "CashierClosureLastDto [closed=" + closed + ", closureDate=" + closureDate + "]";
    }

}