import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Appointment {
    String date;
    String time;

    public Appointment() {
        LocalDate appointmentDate = LocalDate.now().plusWeeks(1);
        this.date = appointmentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.time = constants.time;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

}
