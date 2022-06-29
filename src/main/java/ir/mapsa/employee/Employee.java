package ir.mapsa.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_employee")
public class Employee {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false)
   private Long id;

   @Column(name = "firstName")
   private String firstName;
   @Column(name = "lastName")
   private String lastName;
   @Column(name = "nationalCode")
   private long nationalCode;
   @Column(name = "single")
   private boolean single;
   @Column(name = "children")
   private int children;
   @Column(name = "birthday")
   private Date birthday;


}
