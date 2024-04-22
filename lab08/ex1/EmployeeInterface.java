package ex1;

import java.time.LocalDateTime;

public interface EmployeeInterface {
    public void start (LocalDateTime date);
    public void terminate(LocalDateTime date);
    public boolean work();
}
