package com.rs.faces.webservices;

import com.rs.faces.webservices.wsmodels.WSError;

public interface WebServiceErrorListener {

    void errorResponse(WSError error);
}
