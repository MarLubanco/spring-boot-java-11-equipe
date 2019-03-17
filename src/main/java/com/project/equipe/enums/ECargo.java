package com.project.equipe.enums;

public enum ECargo {

  VENDEDOR() {
    @Override
    public double applySalario(double salario) {
      return salario + (salario * 0.15);
    }
  },
  ASSISTENTE() {
    @Override
    public double applySalario(double salario) {
      return salario + (salario * 0.20);
    }
  },
  OPERADOR() {
    @Override
    public double applySalario(double salario) {
      return salario + (salario * 0.19);
    }
  },
  SUPORTE() {
    @Override
    public double applySalario(double salario) {
      return salario + (salario * 0.05);
    }
  };

  private double salario;

  public abstract double applySalario(double salario);
}
