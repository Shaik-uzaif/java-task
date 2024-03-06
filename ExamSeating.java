// EXAM SEATING ARRANGEMENT

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Seat {
    int seatNumber;
    Student student;

    Seat(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    void assignStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Seat " + seatNumber + ": " + (student != null ? student.name : "Empty");
    }
}

public class ExamSeating {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("john"));
        students.add(new Student("rock"));
        students.add(new Student("bravo"));
        students.add(new Student("ezra"));
        students.add(new Student("Charles"));
        students.add(new Student("Alexander"));
        students.add(new Student("Lucas"));
        students.add(new Student("William"));
        students.add(new Student("Henry"));
        students.add(new Student("Tom"));
        int totalSeats = 10;
        List<Seat> seats = new ArrayList<>();

        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat(i));
        }

        Collections.shuffle(students);

        for (int i = 0; i < Math.min(students.size(), totalSeats); i++) {
            seats.get(i).assignStudent(students.get(i));
        }

        for (Seat seat : seats) {
            System.out.println(seat);
        }
    }
}
