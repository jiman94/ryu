package com.msa.template.core.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(staticName = "of")
@ToString
public class Pentuple<F, S, T, E, V> {

private F first;
private S second;
private T third;
private E fourth;
private V fifth;
}
