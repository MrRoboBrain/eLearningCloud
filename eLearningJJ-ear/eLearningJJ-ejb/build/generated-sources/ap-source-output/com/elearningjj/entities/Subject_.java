package com.elearningjj.entities;

import com.elearningjj.entities.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-02T16:45:46")
@StaticMetamodel(Subject.class)
public class Subject_ { 

    public static volatile SingularAttribute<Subject, String> subjectCode;
    public static volatile SingularAttribute<Subject, User> userId;
    public static volatile SingularAttribute<Subject, Integer> subjectId;
    public static volatile SingularAttribute<Subject, String> subjectName;

}