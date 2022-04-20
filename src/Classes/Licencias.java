/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;


/**
 *
 * @author OSBAL
 */
public class Licencias {
    
    public boolean validar_serial(String[] serial) {
        int fase = 0;
        boolean resp = true;
        boolean[] cumple = new boolean[serial.length];
        for (int i = 0; i < serial.length; i++) {
            char[] word = serial[i].toCharArray();
            switch (fase) {
                case 0:
                    if (!Character.isDigit(word[0])) {
                        if (!Character.isDigit(word[1])) {
                            if (Character.isDigit(word[2])) {
                                if (Character.isDigit(word[3])) {
                                    cumple[i] = true;
                                } else {
                                    cumple[i] = false;
                                }
                            } else {
                                cumple[i] = false;
                            }
                        } else {
                            cumple[i] = false;
                        }
                    } else {
                        cumple[i] = false;
                    }
                    fase++;
                    break;
                case 1:
                    if (Character.isDigit(word[0])) {
                        if (Character.isDigit(word[1])) {
                            if (!Character.isDigit(word[2])) {
                                if (!Character.isDigit(word[3])) {
                                    cumple[i] = true;
                                } else {
                                    cumple[i] = false;
                                }
                            } else {
                                cumple[i] = false;
                            }
                        } else {
                            cumple[i] = false;
                        }
                    } else {
                        cumple[i] = false;
                    }
                    fase++;
                    break;
                case 2:
                    if (Character.isDigit(word[0])) {
                        if (!Character.isDigit(word[1])) {
                            if (!Character.isDigit(word[2])) {
                                if (!Character.isDigit(word[3])) {
                                    cumple[i] = true;
                                } else {
                                    cumple[i] = false;
                                }
                            } else {
                                cumple[i] = false;
                            }
                        } else {
                            cumple[i] = false;
                        }
                    } else {
                        cumple[i] = false;
                    }
                    fase++;
                    break;
                case 3:
                    if (!Character.isDigit(word[0])) {
                        if (!Character.isDigit(word[1])) {
                            if (!Character.isDigit(word[2])) {
                                if (!Character.isDigit(word[3])) {
                                    cumple[i] = true;
                                } else {
                                    cumple[i] = false;
                                }
                            } else {
                                cumple[i] = false;
                            }
                        } else {
                            cumple[i] = false;
                        }
                    } else {
                        cumple[i] = false;
                    }
                    fase++;
                    break;
                case 4:
                    if (!Character.isDigit(word[0])) {
                        if (Character.isDigit(word[1])) {
                            if (Character.isDigit(word[2])) {
                                if (Character.isDigit(word[3])) {
                                    cumple[i] = true;
                                } else {
                                    cumple[i] = false;
                                }
                            } else {
                                cumple[i] = false;
                            }
                        } else {
                            cumple[i] = false;
                        }
                    } else {
                        cumple[i] = false;
                    }
                    fase++;
                    break;
                case 5:
                    if (!Character.isDigit(word[0])) {
                        if (!Character.isDigit(word[1])) {
                            if (!Character.isDigit(word[2])) {
                                if (Character.isDigit(word[3])) {
                                    if (Character.isDigit(word[4])) {
                                        cumple[i] = true;
                                    } else {
                                        cumple[i] = false;
                                    }
                                } else {
                                    cumple[i] = false;
                                }
                            } else {
                                cumple[i] = false;
                            }
                        } else {
                            cumple[i] = false;
                        }
                    } else {
                        cumple[i] = false;
                    }
                    fase++;
                    break;
            }
        }
        for (int i = 0; i < cumple.length; i++) {
            if (!cumple[i]) {
                return false;
            }
        }
        return resp;
    }
}
