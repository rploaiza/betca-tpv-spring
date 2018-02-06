package es.upm.miw.dtos.input;

import java.math.BigDecimal;

public class CashierClosureDto {

    private BigDecimal finalCash;

    private BigDecimal salesCard;

    private String comment;

    public CashierClosureDto() {
    }

    public CashierClosureDto(BigDecimal finalCash, BigDecimal salesCard, String comment) {
        this.finalCash = finalCash;
        this.salesCard = salesCard;
        this.comment = comment;
    }

    public BigDecimal getFinalCash() {
        return finalCash;
    }

    public void setFinalCash(BigDecimal finalCash) {
        this.finalCash = finalCash;
    }

    public BigDecimal getSalesCard() {
        return salesCard;
    }

    public void setSalesCard(BigDecimal salesCard) {
        this.salesCard = salesCard;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
