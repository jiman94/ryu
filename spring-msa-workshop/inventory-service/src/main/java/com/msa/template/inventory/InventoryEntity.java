package com.msa.template.inventory;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;


@Table(name = "`inventory`")
@Entity
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Comment("일련번호")
	private Long id;

	@Column(name = "sku_code")
	@Comment("SKU 코드")
	private String skuCode;

	@Column(name = "stock")
	@Comment("재고")
	private Integer stock;
}
