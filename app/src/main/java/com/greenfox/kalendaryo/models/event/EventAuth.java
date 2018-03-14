package com.greenfox.kalendaryo.models.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Lilla on 2018. 03. 14..
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventAuth {

     String id;
     String email;
     String displayName;
     boolean self;
}
