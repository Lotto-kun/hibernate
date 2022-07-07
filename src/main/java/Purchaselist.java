import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "purchaselist")
public class Purchaselist {
    @EmbeddedId
    private PurchasePK id;
    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;
    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;
    private Integer price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public PurchasePK getId() {
        return id;
    }

    public void setId(PurchasePK id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
