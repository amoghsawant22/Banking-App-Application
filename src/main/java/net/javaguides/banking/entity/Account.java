package net.javaguides.banking.entity;


import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="accounts")
@Entity
public class Account {

    @Id


    private Long id;

    @Column(name ="account_holder_name")
    private String accountHolderName;
    private double balance;

//    @OneToOne
//    @JoinColumn (name ="useridf")
//    private UserDetails user;
}
