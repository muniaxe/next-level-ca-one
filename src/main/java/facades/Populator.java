/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Member;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author tha
 */
public class Populator {
    public static void populate() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        MemberFacade fe = MemberFacade.getFacadeExample(emf);
        fe.create(new Member("Jack Hagedorn Jensen", "cph-jj484@cphbusiness.dk", new ArrayList<>(Arrays.asList("DOTA 2", "Hunt: Showdown", "Path of Exile"))));
        fe.create(new Member("Emil Dyrhøi Tolderlund Jørgensen", "cph-ej142@cphbusiness.dk", new ArrayList<>(Arrays.asList("WoW", "osu!", "CS:GO"))));
        fe.create(new Member("Mathias Hvid", "cph-mh881@cphbusiness.dk", new ArrayList<>(Arrays.asList("Runescape", "CS:GO", "Diablo: All of them"))));
    }

    public static void main(String[] args) {
        populate();
    }
}
