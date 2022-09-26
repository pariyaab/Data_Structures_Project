// Copyright (C) Kamaledin Ghiasi-Shirazi, Ferdowsi Univerity of Mashhad, 2020 (1399 Hijri Shamsi)
//
// Authors: Ali Moghaddas-zadeh
//			Fateme Chaji

#pragma once

class I{
private:
	int value;

public:
	static int counter;

public:
	I(const I& i);
	I(int val);
	I();
	~I();

	bool operator>(const I& right) const;
	bool operator<(const I& right) const;
	bool operator>=(const I& right) const;
	bool operator<=(const I& right) const;
	bool operator==(const I& right) const;
	bool operator!=(const I& right) const;
	I& operator=(const I& right);
	operator double() const;

	int getValue() const;
};
