import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.List;
import java.util.Scanner;


public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final Marker EXCEPTIONS_MARKER = MarkerFactory.getMarker("EXCEPTIONS");

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        String enterId;
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.println("Введите id курса (от 1 до 46) или 0 для выхода");
            enterId = scanner.next();
            if (enterId.equals("0")) {
                break;
            }
            try {
                Course course = session.get(Course.class, enterId);
                System.out.println("Имя курса: " + course.getName());
                System.out.println("Учитель: " + course.getTeacher().getName());
                System.out.println("Описание курса: " + course.getDescription());
                System.out.println("Количество студентов курса: " + course.getStudentsCount());
                List<Student> courseStudentList = course.getStudents();
                System.out.println("Список студентов курса: ");
                courseStudentList.forEach(student -> System.out.println(student.getName()));

                System.out.println("Введите ID студента (от 1 до 100) или 0 для выхода");
                enterId = scanner.next();
                System.out.println("Студент " + session.get(Student.class, enterId).getName() + " подписан на курсы:");
                List<Subscription> subscriptions = session.createQuery("from Subscription where studentId = " + enterId, Subscription.class).list();

                subscriptions.forEach(sub ->
                        System.out.println(session.get(Course.class, sub.getId().getCourseId()).getName() +
                                ". Дата подписки " + sub.getSubscriptionDate())
                );
            } catch (Exception e) {
                LOGGER.error(EXCEPTIONS_MARKER, "Ошибка при обращении к таблицам по Id", e);
                e.printStackTrace();
            }
        }

        sessionFactory.close();
        registry.close();


    }
}
