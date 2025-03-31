package model;
import java.sql.Timestamp;

public class Assignment {
    private int id;
    private int facultyId;
    private int courseId;
    private String title;
    private String description;
    private String filename;
    private String filepath;
    private Timestamp uploadDate;
    private String facultyName; // For displaying faculty name in JSP
    private String courseName;  // For displaying course name in JSP

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getFacultyId() { return facultyId; }
    public void setFacultyId(int facultyId) { this.facultyId = facultyId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }

    public String getFilepath() { return filepath; }
    public void setFilepath(String filepath) { this.filepath = filepath; }

    public Timestamp getUploadDate() { return uploadDate; }
    public void setUploadDate(Timestamp uploadDate) { this.uploadDate = uploadDate; }

    public String getFacultyName() { return facultyName; }
    public void setFacultyName(String facultyName) { this.facultyName = facultyName; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
}
