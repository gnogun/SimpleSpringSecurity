package com.spring.jwt.sample.service;

//@Service
//jwt 사용으로 인해 deprecated됨.
@Deprecated
public class AuthService {

//	@Autowired
//	private AuthenticationManager authenticationManager;
//
////	@Autowired
////	private RememberMeServices customSecurityRememberMeService;
//
//	public Authentication customLogin(String id, String pwd, AuthEnum common, HttpServletRequest request,
//			HttpServletResponse response) throws BadCredentialsException, UsernameNotFoundException {
//		CustomAuthenticationToken token = new CustomAuthenticationToken(id, pwd);
//
//		Authentication authentication = authenticationManager.authenticate(token);
//
//		if (authentication != null) {
//			HttpSession session = request.getSession();
//
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
//					SecurityContextHolder.getContext());
//
////			customSecurityRememberMeService.loginSuccess(request, response, authentication);
//
//			return authentication;
//
//		} else {
//			throw new IllegalArgumentException("account doesn't exists. Please check userid, password");
//		}
//
//	}
}
