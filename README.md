# Dept Calculator

## What is it?
Code to compute the total amount of money owed including compounded interest over time.

## What is the goal?
Provide an accurate calculation of a debt that includes compounded interest, accumulated over an extended period of time from a set of operations that include both withdrawals that increase the debt and deposits that reduce it, with changing interest rates.

## Who uses it?
This was written to calculate the amount owed based on the use of a credit line with variable interest rate.

## How does it work?
- An `Operation` can be either a `TransactionOperation` or a `RateChangeOperation`.
  - The `TransactionOperation` can be a **withdrawal** or a **deposit** that respectively increase or reduce the _capital_ owed. 
  - The `RateChangeOperation` defines the date and new rate when there is a change in the interest rate.
- The `OperationFileReader` reads a CSV file that contains the operations and creates an `ArrayList<Operation>` with the data contained in 4 columns: 
  - Date (format is `26 Jul 2023` or `"dd MMM uuuu"`)
  - Description (string description)
  - Rate (variable annual interest rate)
  - Amount (non-null for transactions and null for rate changes)
- The `DebtCalculator` takes the list of operations and computes the `totaCapital`, `totalInterest`, and their sum, `balanceOwed`. The interest is compounded on a daily basis, which approximates very closely monthly compounding.


