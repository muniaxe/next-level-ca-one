/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tha
 */
public class MemberDTO {
    private long id;
    private String name;
    private List<String> favoriteGames;

    public MemberDTO(Member mem) {
        this.id = mem.getId();
        this.name = mem.getName();
        this.favoriteGames = mem.getFavoriteGames();
    }

    public static List<MemberDTO> getDtos(List<Member> mems) {
        List<MemberDTO> memdtos = new ArrayList();
        mems.forEach(mem -> memdtos.add(new MemberDTO(mem)));
        return memdtos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFavoriteGames() {
        return favoriteGames;
    }

    public void setFavoriteGames(List<String> favoriteGames) {
        this.favoriteGames = favoriteGames;
    }

    public long getId() {
        return id;
    }

}
