package business.server.model;

import business.server.endpoint.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by bishruti on 2/3/16.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class User implements Serializable {
    private int uId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String bloodGroup;
    private String address;
    private String quote;
    private ArrayList<String> goal;
    private ArrayList<String> activity;

    @XmlElementWrapper(name = "currentHealth")
    private CurrentHealth currentHealth;
    public CurrentHealth  getCurrentHealth() {
        return currentHealth;
    }
    public void setCurrentHealth(CurrentHealth currentHealth) {
        this.currentHealth = currentHealth;
    }

    private FoodSuggestion foodSuggestion;
    public FoodSuggestion getFoodSuggestion() {
        return foodSuggestion;
    }
    public void setFoodSuggestion(FoodSuggestion foodSuggestion) {
        this.foodSuggestion = foodSuggestion;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getGoal() {
        return goal;
    }

    public void setGoal(ArrayList<String> goal) {
        this.goal = goal;
    }

    public ArrayList<String> getActivity() {
        return activity;
    }

    public void setActivity(ArrayList<String> activity) {
        this.activity = activity;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public static User getUserDetail(int uId) throws Exception {
        User user = UserImplementation.getUserDetail(uId);
        user.setQuote(Quote.getQuote());
        user.setFoodSuggestion(FoodRecommendation.getFoodRecomm("low-fat"));
        ArrayList<String> userGoals = new ArrayList<String>();
        userGoals.add(GoalImplementation.getGoalByName("swim-learn").getGoalDescription());
        userGoals.add(GoalImplementation.getGoalByName("dec-weight-less").getGoalDescription());
        user.setGoal(userGoals);
        ArrayList<String> userActivities = new ArrayList<String>();
        userActivities.add(ActivityImplementation.getActivityByName("run-min").getActivityDescription());
        userActivities.add(ActivityImplementation.getActivityByName("swim-min").getActivityDescription());
        user.setActivity(userActivities);
        return user;
    }
}
