package com.example.meeting;

import com.example.meeting.dto.ReunionRequestDTO;
import com.example.meeting.dto.SalleRequestDTO;
import com.example.meeting.entities.Reunion;
import com.example.meeting.service.IReunionServices;
import com.example.meeting.service.ISalleServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableAspectJAutoProxy
@SpringBootApplication
public class MeetingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetingApplication.class, args);
    }



    @Bean
    CommandLineRunner runner (ISalleServices iSalleServices , IReunionServices iReunionServices){
        return  args -> {
            List<String> list =new ArrayList<>();
            list.add("Néant");
            iSalleServices.ajoutSalle(new SalleRequestDTO("E1001",23,list));
            list.clear();
            list.add("Ecran");
            iSalleServices.ajoutSalle(new SalleRequestDTO("E1002",10,list));
            list.clear();
            list.add("Pieuvre");
            iSalleServices.ajoutSalle(new SalleRequestDTO("E1003",8,list));
            list.clear();
            list.add("Tableau");
            iSalleServices.ajoutSalle(new SalleRequestDTO("E1004",4,list));
            list.clear();
            list.add("Néant");
            iSalleServices.ajoutSalle(new SalleRequestDTO("E2001",4,list));
            list.clear();
            list.add("Ecran");
            list.add("Webcam");
            iSalleServices.ajoutSalle(new SalleRequestDTO("E2002",15,list));
            list.clear();
            list.add("Néant");
            iSalleServices.ajoutSalle(new SalleRequestDTO("E2003",7,list));
            list.clear();
            list.add("Tableau");
            iSalleServices.ajoutSalle(new SalleRequestDTO("E2004",9,list));
            list.clear();
            list.add("Ecran");
            list.add("Webcam");
            list.add("Pieuvre");
            iSalleServices.ajoutSalle(new SalleRequestDTO("E3001",13,list));
            list.clear();
            list.add("Néant");
            iSalleServices.ajoutSalle(new SalleRequestDTO("E3002",8,list));
            list.clear();
            list.add("Ecran");
            list.add("Pieuvre");
            iSalleServices.ajoutSalle(new SalleRequestDTO("E3003",9,list));
            list.clear();
            list.add("Néant");
            iSalleServices.ajoutSalle(new SalleRequestDTO("E3004",4,list));


            /*
            iReunionServices.ajoutReunion(new ReunionRequestDTO());
            iReunionServices.ajoutReunion(new ReunionRequestDTO());
            iReunionServices.ajoutReunion(new ReunionRequestDTO());
            iReunionServices.ajoutReunion(new ReunionRequestDTO());
            iReunionServices.ajoutReunion(new ReunionRequestDTO());
            */



        };
    }

}
