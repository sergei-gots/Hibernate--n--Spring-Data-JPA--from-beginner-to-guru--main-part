package guru.springframework.jdbc.domain;

import jakarta.persistence.Entity;

import java.util.Objects;

/**
 * Created by sergei on 09/03/2025
 */
@Entity
public class OrderApproval extends BaseEntity{

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderApproval that = (OrderApproval) o;
        return Objects.equals(approvedBy, that.approvedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), approvedBy);
    }

    private String approvedBy;

    @Override
    public String toString() {
        return "OrderApproval{" +
                "approvedBy='" + approvedBy + '\'' +
                "} " + super.toString();
    }
}
