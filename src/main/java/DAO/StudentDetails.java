package DAO;

public class StudentDetails {
    private int userId;
    private String fullName;
    private String fatherName;
    private String address;
    private String phoneNumber;
    private String email;
    private String aadharCard;
    private String panCard;
    private String course;
    private String department;

    // Default constructor
    public StudentDetails() {}

    // Parameterized constructor
    public StudentDetails(int userId, String fullName, String fatherName, String address,
                          String phoneNumber, String email, String aadharCard, String panCard,
                          String course, String department) {
        this.userId = userId;
        this.fullName = fullName;
        this.fatherName = fatherName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.aadharCard = aadharCard;
        this.panCard = panCard;
        this.course = course;
        this.department = department;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAadharCard() {
        return aadharCard;
    }

    public void setAadharCard(String aadharCard) {
        this.aadharCard = aadharCard;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
