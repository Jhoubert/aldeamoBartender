package com.aldeamo.BartenderTest.ws;

import com.aldeamo.BartenderTest.exceptions.IdNotFound;
import com.aldeamo.BartenderTest.response.BartenderResponse;

public interface IBartenderWS {
    public BartenderResponse execute(int q, int id) throws IdNotFound;
}
