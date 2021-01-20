package com.github.qikangchen.spring.data.normalized.db.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location{

    private String latitude;
    private String longitude;

}
