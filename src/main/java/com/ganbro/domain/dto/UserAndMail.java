package com.ganbro.domain.dto;

import com.ganbro.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndMail extends User {
    String mail;
}
