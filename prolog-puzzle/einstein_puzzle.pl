% The Einstein/zebra puzzle, Prolog solution by Colten Duccini

% the house predicate, with the house number and the 5 predicates
house(N,A,B,C,D,E) :-
    member(N, [1,2,3,4,5]),
    member(A, [englishman, spaniard, norwegian, ukrainian, japanese]),
    member(B, [red, green, ivory, yellow, blue]),
    member(C, [dog, fox, horse, snails, zebra]),
    member(D, [hershey, kit_kats, smarties, snickers, milky_ways]),
    member(E, [water, milk, coffee, tea, oj]).

% creates a list of houses
listHouses(0, []).
listHouses(N, [house(Y,_,_,_,_,_)|T]) :- N > 0, Y is 6 - N, M is N - 1,listHouses(M, T).

leftOf(A, B, X) :- append(_, [A, B|_], X).
nextTo(A, B, X) :- leftOf(A, B, X) ; leftOf(B, A, X).

% we assume from facts 3 and 7 that 2 is the blue house 
% we also have to limit our solution to length 5.
solution(X) :-
    length(X,5),
    listHouses(5, X),
    member(house(1,  norwegian,      _,      _,        _,      _), X),
    member(house(2,     _,        blue,      _,        _,      _), X),
    member(house(3,          _,      _,      _,        _,   milk), X),
    member(house(_, englishman,    red,      _,        _, _     ), X),
    member(house(_,   spaniard,      _,    dog,        _, _     ), X),
    member(house(_,          _, yellow,      _, kit_kats, _     ), X),
    member(house(_,          _,      _, snails, smarties, _     ), X),
    member(house(_,          _,      _,      _, snickers, oj    ), X),
    member(house(_,  ukrainian,      _,      _,        _, tea   ), X),
    member(house(_,   japanese,      _,      _, milky_ways,    _), X),
    member(house(_,          _,  green,      _,        _, coffee), X),
    leftOf(house(_,          _,  ivory,      _,        _,      _),
	   house(_,          _,  green,      _,        _,      _), X),
    nextTo(house(_,          _,      _,    fox,        _,      _),
	   house(_,          _,      _,      _,  hershey,      _), X),
    nextTo(house(_,          _,      _,  horse,        _,      _),
	   house(_,          _,      _,      _, kit_kats,      _), X),
    member(house(_,          _,      _,  zebra,        _,      _), X),    
    member(house(_,          _,      _,       _,       _,  water), X).
