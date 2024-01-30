package com.school.sba.requestdto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ClassHourRequest {
private int classHourId;
private int teacherId;
private int subjectId;
private int roomNum;
}
