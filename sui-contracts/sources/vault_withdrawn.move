// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module sui_blog_example::vault_withdrawn {

    use sui::object;
    use sui_blog_example::blog::{Self, VaultWithdrawn};

    public fun id(vault_withdrawn: &VaultWithdrawn): object::ID {
        blog::vault_withdrawn_id(vault_withdrawn)
    }

    public fun amount(vault_withdrawn: &VaultWithdrawn): u64 {
        blog::vault_withdrawn_amount(vault_withdrawn)
    }

}