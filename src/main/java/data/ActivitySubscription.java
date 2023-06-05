package data;

public class ActivitySubscription {

    final String activityID;
    final double amountPaid;

    public ActivitySubscription(String activityID, double amountPaid) {
        this.activityID = activityID;
        this.amountPaid = amountPaid;
    }

    public String getActivityID() {
        return activityID;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ActivitySubscription other = (ActivitySubscription) obj;
        return activityID.equals(other.activityID);
    }

    @Override
    public int hashCode() {
        return activityID.hashCode();
    }

}
