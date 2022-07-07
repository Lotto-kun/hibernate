import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;
    private String description;
    private Integer duration;
    private Integer price;
    @Column(name = "price_per_hour")
    private float pricePerHour;
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;
    @Column(name = "students_count")
    private Integer studentsCount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name= "subscriptions",
            joinColumns = {@JoinColumn(name="course_id")},
            inverseJoinColumns = {@JoinColumn(name="student_id")}
    )
    private List<Student> courseStudentsList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(Integer studentsCount) {
        this.studentsCount = studentsCount;
    }

    public List<Student> getStudents() {
        return courseStudentsList;
    }
}


