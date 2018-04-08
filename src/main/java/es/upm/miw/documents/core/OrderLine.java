package es.upm.miw.documents.core;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class OrderLine {

    @DBRef
    private Article article;
    
    private int requiredAmount;
    
    private int finalAmount;

    public OrderLine(Article article, int requiredAmount) {
        this.article = article;
        this.requiredAmount = requiredAmount;
    }

    public int getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(int finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Article getArticle() {
        return article;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    @Override
    public String toString() {
        return "OrderLine [article=" + article + ", requiredAmount=" + requiredAmount + ", finalAmount=" + finalAmount + "]";
    }
    
}
