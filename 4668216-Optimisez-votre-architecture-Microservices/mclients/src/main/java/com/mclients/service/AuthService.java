package com.mclients.service;

import com.mclients.dto.RegisterDTO;
import com.mclients.dto.RegisterResponse;

public interface AuthService {
	
	RegisterResponse register(RegisterDTO registerDTO);

}
