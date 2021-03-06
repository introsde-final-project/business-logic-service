package business.server.model;

import business.server.endpoint.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        Float systolicBloodpressure = null;
        Float diastolicBloodpressure = null;
        Float bmi = null;
        ArrayList<String> userGoals = new ArrayList<String>();
        ArrayList<String> userActivities = new ArrayList<String>();

        user.setQuote(Quote.getQuote());

        List<HealthProfile> healthProfiles = user.getCurrentHealth().getMeasureType();
        for (HealthProfile healthProfile : healthProfiles) {
            if (healthProfile.getMeasureType().equalsIgnoreCase("systolic-bloodpressure")) {
                systolicBloodpressure = Float.parseFloat(healthProfile.getMeasureValue());
            }
            if (healthProfile.getMeasureType().equalsIgnoreCase("diastolic-bloodpressure")) {
                diastolicBloodpressure = Float.parseFloat(healthProfile.getMeasureValue());
            }
            if (healthProfile.getMeasureType().equalsIgnoreCase("bmi")) {
                bmi = Float.parseFloat(healthProfile.getMeasureValue());
            }
        }

        if (systolicBloodpressure < 70.0 || diastolicBloodpressure < 40.0) {
            userGoals.add(GoalImplementation.getGoalByName("extremely-low").getGoalDescription());
            userActivities.add(ActivityImplementation.getActivityByName("see-doctor").getActivityDescription());
        }

        else if ((systolicBloodpressure >= 70.0 && systolicBloodpressure < 90.0) || (diastolicBloodpressure >= 40 && diastolicBloodpressure < 60)) {
            userGoals.add(GoalImplementation.getGoalByName("low-bp").getGoalDescription());
            userActivities.add(ActivityImplementation.getActivityByName("safety-first").getActivityDescription());
            userActivities.add(ActivityImplementation.getActivityByName("consult-doctor").getActivityDescription());
        }

        else if ((systolicBloodpressure >= 90.0 && systolicBloodpressure <= 120.0) || (diastolicBloodpressure >= 60 && diastolicBloodpressure <= 80)) {
            userGoals.add(GoalImplementation.getGoalByName("normal").getGoalDescription());
            userActivities.add(ActivityImplementation.getActivityByName("do-mini-workout").getActivityDescription());
            userActivities.add(ActivityImplementation.getActivityByName("play-team-sports").getActivityDescription());
        }

        else if ((systolicBloodpressure > 120.0 && systolicBloodpressure <= 140.0) || (diastolicBloodpressure > 80 && diastolicBloodpressure <= 90)) {
            userGoals.add(GoalImplementation.getGoalByName("pre-high").getGoalDescription());
            userActivities.add(ActivityImplementation.getActivityByName("strength-training-highbp").getActivityDescription());
            userActivities.add(ActivityImplementation.getActivityByName("isometric-exercise").getActivityDescription());
        }

        else if ((systolicBloodpressure > 140.0 && systolicBloodpressure <= 180.0) || (diastolicBloodpressure > 90 && diastolicBloodpressure <= 100)) {
            userGoals.add(GoalImplementation.getGoalByName("high-bp").getGoalDescription());
            userActivities.add(ActivityImplementation.getActivityByName("highbp-limit").getActivityDescription());
            userActivities.add(ActivityImplementation.getActivityByName("highbp-moderate").getActivityDescription());
        }
        else {
            userGoals.add(GoalImplementation.getGoalByName("extremely-high").getGoalDescription());
            userActivities.add(ActivityImplementation.getActivityByName("see-doctor").getActivityDescription());
        }

        if (bmi < 18.5) {
            userGoals.add(GoalImplementation.getGoalByName("under-weight").getGoalDescription());
            user.setFoodSuggestion(FoodRecommendation.getFoodRecomm("high-protein"));
        }
        else if (bmi >= 18.5 && bmi < 25) {
            userGoals.add(GoalImplementation.getGoalByName("healthy").getGoalDescription());
            user.setFoodSuggestion(FoodRecommendation.getFoodRecomm("balanced"));
        }
        else if (bmi >= 25 && bmi <= 30) {
            userGoals.add(GoalImplementation.getGoalByName("over-weight").getGoalDescription());
            user.setFoodSuggestion(FoodRecommendation.getFoodRecomm("low-carb"));
        }
        else if (bmi >= 30 && bmi <= 35) {
            userGoals.add(GoalImplementation.getGoalByName("obese").getGoalDescription());
            user.setFoodSuggestion(FoodRecommendation.getFoodRecomm("low-fat"));
        }
        else {
            userGoals.add(GoalImplementation.getGoalByName("very-obese").getGoalDescription());
            user.setFoodSuggestion(FoodRecommendation.getFoodRecomm("low-fat-abs"));
        }

        user.setGoal(userGoals);
        user.setActivity(userActivities);
        return user;
    }
}
