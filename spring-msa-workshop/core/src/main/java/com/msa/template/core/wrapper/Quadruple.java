package com.msa.template.core.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(staticName = "of")
@ToString
public class Quadruple<F, S, T, E> {

private F first;
private S second;
private T third;
private E fourth;
}
