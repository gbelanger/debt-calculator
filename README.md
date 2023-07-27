# Debt Calculator

## What is it?
Code to compute the total amount of money owed including compounded interest over time. 
It was written to calculate the amount owed based on the use of a credit line with variable interest rate over a decade.

## What is the goal?
Provide an accurate calculation of a debt that includes compounded interest, accumulated over an extended period of time from a set of operations that include both withdrawals that increase the debt and deposits that reduce it, with changing interest rates.

## How does it work?
- An `Operation` can be either a `TransactionOperation` or a `RateChangeOperation`.
  - The `TransactionOperation` can be a **withdrawal** or a **deposit** that respectively increases or reduces the _capital_ owed. 
  - The `RateChangeOperation` defines the date and new rate when there is a change in the interest rate.
- The `OperationFileReader` reads a CSV file that contains the operations and creates an `ArrayList<Operation>` with the data contained in 4 columns: 
  1. Date (format is `26 Jul 2023` or `"dd MMM uuuu"`)
  2. Description (string description)
  3. Rate (variable annual interest rate)
  4. Amount (non-null for transactions and null for rate changes)
- The `DebtCalculator` takes the list of operations (constructor takes `ArrayList<Operation>`) and computes the `totaCapital`, `totalInterest`, and their sum, `balanceOwed`. The interest is compounded on a daily basis, which approximates very closely monthly compounding.

#### Input CSV file Format example
This sample spans 9 years, and shows examples of the three types of operations that include withdrawals, deposits, and interest rate changes.
```
Date,Description,AnnualRate,Amount
28 May 2014,ABM Cash Withdrawal,3.5,500.00
12 Sep 2014,EB BILL PYMT VISA,3.5,550.99
15 Sep 2014,Pre-Auth Pymt CEPEO,3.5,136.30
28 Jan 2015,Interest rate change,3.35,
03 Jun 2015,Purchase AutoPro,3.35,600.00 
16 Dec 2015,Purchase Provigo LeMarche,3.20,17.53 
07 Mar 2016,Purchase Cinema des Galleries,3.20,51.52
07 Mar 2016,ABM Cash Withdrawal CDS,3.20,504.00
07 Mar 2016,Service Charge,3.20,1.50
01 May 2016,Interest rate change,3.20,
23 Feb 2017,Purchase Uniprix,3.20,7.69
27 Feb 2017,Purchase Canadian Tire Gas Bar,3.20,31.01
27 Feb 2017,Purchase Petro Canada,3.20,52.52
25 Oct 2018,Interest rate change,4.45,
02 Nov 2018,Purchase Clinique Dentaire,4.45,398.00
09 Nov 2018,Purchase Costco Wholesale,4.45,210.04
30 Sep 2019,Purchase Marche LaFlame,4.45,4.09
08 Nov 2019,Purchase A&W Resto Vanier,4.45,32.15
10 Feb 2020,Purchase IGA Extra Grenier Fort,4.45,94.00
06 Mar 2020,Interest rate change,3.95,
02 Jun 2022,Interest rate change,4.20,
16 Jun 2022,Purchase Dental Clinic Prestige,4.20,740.00
14 Jul 2022,Interest rate change,5.20,
15 Sep 2022,Repayment e-Transfer,5.95,-1276.84
27 Oct 2022,Interest rate change,6.45,
10 Nov 2022,Repayment e-Transfer,6.45,-207
09 May 2023,Purchase Canadian Tire Gas Bar,7.20,65.04
08 Jun 2023,Interest rate change,7.45,
```

## How to use it?
1. Clone `git clone git@github.com:gbelanger/debt-calculator.git`
2. Compile `cd debt-calculator ; javac *.java`
3. Run `java ComputeDebt /path/to/file`
