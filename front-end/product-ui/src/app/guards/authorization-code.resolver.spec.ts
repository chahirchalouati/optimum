import {TestBed} from '@angular/core/testing';

import {AuthorizationCodeResolver} from './authorization-code.resolver';

describe('AuthorizationCodeResolver', () => {
  let resolver: AuthorizationCodeResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(AuthorizationCodeResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
