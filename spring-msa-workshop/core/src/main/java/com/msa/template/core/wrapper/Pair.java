package com.msa.template.core.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(staticName = "of")
@ToString
public class Pair<F, S> {

private F first;
private S second;
}
