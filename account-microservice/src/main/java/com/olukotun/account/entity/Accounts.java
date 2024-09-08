package com.olukotun.account.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Accounts extends BaseEntity{
   private Long customerId;
   @Id
   private Long accountNumber;
   private String  accountType;
   private String branchAddress;
}
