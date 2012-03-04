CREATE ROLE gr_engineerService
	VALID UNTIL 'infinity';

CREATE ROLE br_user LOGIN PASSWORD 'br'
	VALID UNTIL 'infinity'
IN GROUP gr_engineerService;   


